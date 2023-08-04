Import First and Second JSON File for sample in your local desktop for the comparison.

Open Command Prompt,

command: (Windows)

curl -X POST -H "Content-Type: multipart/form-data" -F "inputFile=@\"C:\path\to\first_file.json\"" -F "secondFile=@\"C:\path\to\second_file.json\"" http://localhost:8080/compare


Example:
C:\Users\shali>curl -X POST -H "Content-Type: multipart/form-data" -F "inputFile=@\"C:\Users\shali\Documents\FirstFile.json\"" -F "secondFile=@\"C:\Users\shali\Documents\SecondFile.json\"" http://localhost:8080/compare


Output:
{"areEqual":false,"differences":{"education":{"degree":"Master's","university":"ABC University","graduationYear":2016},"projects":[{"title":"Project C","description":"Desktop Software","teamMembers":["John","Bob"]}]}}


Explanation
--------------
Response :
{
  "name": "Jane Smith",
  "age": 28,
  "email": "jane.smith@example.com",
  "address": {
    "street": "456 Elm Street",
    "city": "Los Angeles",
    "zipcode": "90001"
  },
  "hobbies": ["yoga", "gardening"]
}

this indicates the differences between the two JSON files you provided. Specifically, it shows the contents of the second JSON file that are different from the first JSON file.
