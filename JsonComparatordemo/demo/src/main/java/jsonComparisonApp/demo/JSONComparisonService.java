package jsonComparisonApp.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@Service
public class JSONComparisonService {

    // Custom class to represent the response of JSON comparison
    public static class JSONCompareResult {
        private boolean areEqual;
        private JsonNode differences;

        public JSONCompareResult(boolean areEqual, JsonNode differences) {
            this.areEqual = areEqual;
            this.differences = differences;
        }

        public boolean isAreEqual() {
            return areEqual;
        }

        public JsonNode getDifferences() {
            return differences;
        }
    }

    // Method to compare JSON files received as multipart/form-data
    public JSONCompareResult compareJsonFiles(MultipartFile inputFile, MultipartFile secondFile) {
        try {
            // ObjectMapper Initialization to handle JSON processing
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the content of the uploaded files as JSON
            JsonNode firstJson = objectMapper.readTree(inputFile.getInputStream());
            JsonNode secondJson = objectMapper.readTree(secondFile.getInputStream());

            // Find the differences between the two JSON structures
            JsonNode diffJson = findJsonDifferences(firstJson, secondJson);

            // JSON files are equal based on differences
            boolean areEqual = diffJson == null;

            // Response object
            return new JSONCompareResult(areEqual, diffJson);
        } catch (IOException e) {
            e.printStackTrace();
            // Return null in case of any exceptions
            return null;
        }
    }

    //Recursive method to find differences between JSON nodes
    private JsonNode findJsonDifferences(JsonNode node1, JsonNode node2) {
        if (node1.equals(node2)) {
            return null; // The JSON nodes are equal; no differences found.
        }

        if (node1.isObject() && node2.isObject()) {
            
        	// JSON objects comparison
            ObjectNode diffNode = new ObjectMapper().createObjectNode();

            Iterator<String> fieldNames = node1.fieldNames();
            while (fieldNames.hasNext()){
                String fieldName = fieldNames.next();
                // Recursively compare child nodes
                JsonNode childDiff = findJsonDifferences(node1.get(fieldName), node2.get(fieldName));
                if (childDiff != null) {
                    diffNode.set(fieldName, childDiff);
                }
            }

            return diffNode;
        } else if (node1.isArray() && node2.isArray()) {
            // JSON arrays comparison
            ArrayNode diffArray = new ObjectMapper().createArrayNode();

            int minLength = Math.min(node1.size(), node2.size());
            for (int i = 0; i < minLength; i++) {
                // Recursively compare array elements
                JsonNode childDiff = findJsonDifferences(node1.get(i), node2.get(i));
                if (childDiff != null) {
                    diffArray.add(childDiff);
                }
            }

            return diffArray;
        } else {
            // Different types; return the value from the second JSON
            return node2;
        }
    }
}
