

User Service

## Create User:
## Post request
- http://localhost:8862/user-service/api/auth/register
## Request Body
```JSON
{
  "username": "someone",
  "email": "abc@abc.com",
  "password": "12345678"
}

```
## Response
```JSON
{
  "data": {
    "createdOn": "2023-12-17T13:06:33.764978",
    "updatedOn": "2023-12-17T13:06:33.765031",
    "id": 1,
    "username": "someone",
    "email": "abc@abc.com",
    "password": null
  },
  "message": "user is created"
}				

```

## Error Response
```JSON
{
  "timestamp": "2023-12-17T07:09:17.752+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/user-service/api/auth/register"
}				

```

## Get single user details
## Get request
- http://localhost:8862/user-service/api/auth/user-details/1
## Response
```JSON
{
  "data": {
    "createdOn": "2023-12-17T13:11:32.984961",
    "updatedOn": "2023-12-17T13:11:32.98499",
    "id": 1,
    "username": "someone",
    "email": "abc@abc.com",
    "password": null
  },
  "message": "user information"
}			

```
## Error Response
```JSON
{
  "message": "data not found",
  "httpStatus": "BAD_REQUEST",
  "dateTime": "2023-12-17T13:15:20.987537"
}				

```

## Update a user
## Put request
- http://localhost:8862/user-service/api/auth/edit-user/1
- ## Request Body
```JSON
{
  "username": "someone_updated",
  "email": "abc@abc2.com",
  "password": "12345679"
}

```
## Response
```JSON
{
  "data": {
    "createdOn": "2023-12-17T13:17:03.612474",
    "updatedOn": "2023-12-17T13:17:34.263615",
    "id": 1,
    "username": "someone_updated",
    "email": "abc@abc2.com",
    "password": null
  },
  "message": "user information updated"
}		

```
## Error Response
```JSON
{
  "message": "data not found with given id",
  "httpStatus": "BAD_REQUEST",
  "dateTime": "2023-12-17T13:18:53.825238"
}				

```

## Delete a user
## Delete request
http://localhost:8862/user-service/api/auth/delete-user/1
## Response
```JSON
{
  "data": null,
  "message": "user information deleted"
}

```
## Error Response
```JSON
{
  "message": "data not found with given id",
  "httpStatus": "BAD_REQUEST",
  "dateTime": "2023-12-17T13:20:55.008673"
}			

```

## Deployment

- run mvn clean compile install
- run the docker file
- port is 8862
