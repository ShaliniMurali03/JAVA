package jsonComparisonApp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JsonController {

    private final JSONComparisonService jsonComparisonService;

    @Autowired
    public JsonController(JSONComparisonService jsonComparisonService) {
        this.jsonComparisonService = jsonComparisonService;
    }

    // Endpoint to compare JSON files received as multipart/form-data
    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public ResponseEntity<JSONComparisonService.JSONCompareResult> compareJsonFiles(
            @RequestParam("inputFile") MultipartFile inputFile,
            @RequestParam("secondFile") MultipartFile secondFile) {
        JSONComparisonService.JSONCompareResult result = jsonComparisonService.compareJsonFiles(inputFile, secondFile);
        if (result != null) {
            // Return the custom response object as the response
            return ResponseEntity.ok(result);
        } else {
            // Return an internal server error response in case of any exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
