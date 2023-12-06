## Information
A Rest API that allows 4 endpoints which makes asynchronous calls to H2 database.
This project is using:
- Spring web framework
- H2 in memory database to make database management
- r2dbc to make non-blocking api

## Execution instructions
1. Build the project
2. Run the application
3. The application will start at `http://localhost:8080`
4. Use any API testing tool (e.g., Postman) to call the endpoints

## Endpoints

- `POST /api/file-entries` - Upload CSV file containing file entries (unfortunately there is a bug in file upload functionality in this version of the code)
- `GET /api/file-entries` - Retrieve all file entries
- `GET /api/file-entries/{code}` - Retrieve file entry by code
- `DELETE /api/file-entries` - Delete all file entries


## Test files 
Can be found under test package.
- FileEntryControllerTest
- FileUploadServiceImplTest
- CsvUtilTest

## Potential improvements
- Add pagination to getAll endpoint
- Add index on `code` column to improve the query performance
- Add more test cases, i.e. integration test
- Dockerize the project

