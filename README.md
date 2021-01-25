# Todo Web Services for TodoApp

# REST API Resources
The REST API resources are described below.
### Authentication
#### Request

`POST /security/authentication`

    curl -i -H 'Accept: application/json' -H 'username: madero' -H 'password: madero' http://localhost:8080/TodoRestful/security/authentication

### Response
#### 200 OK
    HTTP/1.1 200 OK
    Status: 200 OK
    Content-Type: application/json
    {
        "username" : "madero",
        "fullname" : "Guillermo Madero Sanchez",
        "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW1vIiwicm9sZXMiOlt7ImlkIjoyLCJyb2xlTmFtZSI6IlVTRVIiLCJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2MTE1MjM0NzgsImV4cCI6MTYxMTUyNDA3OH0.PyF_HlYRaWBYh7dNdR0JOq0CVW1Bblpov6L7vNI_OTo"
    }
#### 401 Unauthorized
    HTTP/1.1 401 Unauthorized
    Status: 401 Unauthorized
    Content-Type: application/json
    {}

## Create a User

### Request

`POST /security/users`

    curl -i -H 'Accept: application/json' -H 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW1vIiwicm9sZXMiOlt7ImlkIjoyLCJyb2xlTmFtZSI6IlVTRVIiLCJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2MTE1MjM0NzgsImV4cCI6MTYxMTUyNDA3OH0.PyF_HlYRaWBYh7dNdR0JOq0CVW1Bblpov6L7vNI_OTo' http://localhost:8080/TodoRestful/security/users
    {
        "username" : "temo",
        "fullname" : "Temo Paez",
        "password" : "temopaez123"
    }
### Response

#### 201 Created
    HTTP/1.1 201 Created
    Content-Type: application/json
    {
        "id": 6,
        "fullname": "Temo paez",
        "username": "temo",
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "enabled": true,
        "modifiedBy": {
            "id": 1,
            "fullname": "Guillermo Madero Sanchez",
            "username": "madero",
            "accountNonExpired": true,
            "accountNonLocked": true,
            "credentialsNonExpired": true,
            "enabled": true,
            "modifiedBy": null,
            "lastModified": "2021-01-24T07:06:00.841+0000",
            "authorities": [
                {
                    "id": 1,
                    "roleName": "ADMIN",
                    "authority": "ADMIN"
                },
                {
                    "id": 2,
                    "roleName": "USER",
                    "authority": "USER"
                }
            ]
        },
        "lastModified": "2021-01-24T19:33:44.694+0000",
        "authorities": [
            {
                "id": 2,
                "roleName": "USER",
                "authority": "USER"
            }
        ]
    }
#### 400 Bad Request   
    HTTP/1.1 400 Bad Request
    Status: 400 Bad Request
    Content-Type: application/json
    {
        "timestamp": "2021-01-25T02:15:03.562+0000",
        "status": 400,
        "error": "Bad Request",
        "message": "Username already exists",
         "path": "/security/users"
    }
#### 401 Unauthorized
    HTTP/1.1 401 Unauthorized
    Status: 401 Unauthorized
    Content-Type: application/json
    {}
## Create a Type

### Request

`POST /management/types`

    curl -i -H 'Accept: application/json' -H 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW1vIiwicm9sZXMiOlt7ImlkIjoyLCJyb2xlTmFtZSI6IlVTRVIiLCJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2MTE1MjM0NzgsImV4cCI6MTYxMTUyNDA3OH0.PyF_HlYRaWBYh7dNdR0JOq0CVW1Bblpov6L7vNI_OTo' http://localhost:8080/TodoRestful/management/types
    {
        "name" : "School"
    }
### Response

#### 201 Created
    HTTP/1.1 201 Created
    Content-Type: application/json
    {
    "id": 1,
    "name": "School",
    "creationDate": "2021-01-24T20:23:04.739+0000",
    "owner": {
        "id": 6,
        "fullname": "Guillermo Madero",
        "username": "madero"
    }
}
#### 401 Unauthorized
    HTTP/1.1 401 Unauthorized
    Status: 401 Unauthorized
    Content-Type: application/json
    {}
    
## Get All Types

### Request

`GET /management/types`

    curl -i -H 'Accept: application/json' -H 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW1vIiwicm9sZXMiOlt7ImlkIjoyLCJyb2xlTmFtZSI6IlVTRVIiLCJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2MTE1MjM0NzgsImV4cCI6MTYxMTUyNDA3OH0.PyF_HlYRaWBYh7dNdR0JOq0CVW1Bblpov6L7vNI_OTo' http://localhost:8080/TodoRestful/management/types

### Response

#### 200 Ok
    HTTP/1.1 200 Ok
    Content-Type: application/json
    [
     {
        "id": 1,
        "name": "School",
        "creationDate": "2021-01-24T20:23:04.739+0000",
        "owner": {
            "id": 6,
            "fullname": "Guillermo Madero",
            "username": "madero"
            }
        },
        {
            "id": 2,
            "name": "Gym",
            "creationDate": "2021-01-24T20:23:04.739+0000",
            "owner": {
                "id": 6,
                "fullname": "Guillermo Madero",
                "username": "madero"
            }
        }
    ]
   
#### 401 Unauthorized
    HTTP/1.1 401 Unauthorized
    Status: 401 Unauthorized
    Content-Type: application/json
    {}
    
## Create a Task

### Request

`POST /management/tasks`

    curl -i -H 'Accept: application/json' -H 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW1vIiwicm9sZXMiOlt7ImlkIjoyLCJyb2xlTmFtZSI6IlVTRVIiLCJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2MTE1MjM0NzgsImV4cCI6MTYxMTUyNDA3OH0.PyF_HlYRaWBYh7dNdR0JOq0CVW1Bblpov6L7vNI_OTo' http://localhost:8080/TodoRestful/management/tasks
    {
        "name" : "Do homework",
        "description" : "Do english homework and send it in a email",
        "expirationDate" : 1611525971536,
        type : {
            "id" : 1
        }
    }

### Response

#### 201 Created
    HTTP/1.1 201 Created
    Content-Type: application/json
    {
        "id": 4,
        "name": "Do homework",
        "description": "Do english homework and send it in a email",
        "creationDate": "2021-01-25T02:36:31.539+0000",
        "expirationDate": "2021-01-29T13:12:51.536+0000",
        "owner": {
            "id": 6,
            "fullname": "Guillermo Madero Sanchez",
            "username": "madero",
            "lastModified": "2021-01-24T19:33:44.694+0000"
            },
        "type": {
            "id": 1,
            "name": "School",
            "creationDate": "2021-01-24T20:20:15.914+0000"
            },
        "status": false
    }

#### 400 Bad Request   
    HTTP/1.1 400 Bad Request
    Status: 400 Bad Request
    Content-Type: application/json
    {
        "timestamp": "2021-01-25T02:15:03.562+0000",
        "status": 400,
        "error": "Bad Request",
        "message": "Expiration date must be after than now",
         "path": "/management/tasks"
    }
   
#### 401 Unauthorized
    HTTP/1.1 401 Unauthorized
    Status: 401 Unauthorized
    Content-Type: application/json
    {}
    
## Finalize Task

### Request

`POST /management/tasks/finalize`

    curl -i -H 'Accept: application/json' -H 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW1vIiwicm9sZXMiOlt7ImlkIjoyLCJyb2xlTmFtZSI6IlVTRVIiLCJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2MTE1MjM0NzgsImV4cCI6MTYxMTUyNDA3OH0.PyF_HlYRaWBYh7dNdR0JOq0CVW1Bblpov6L7vNI_OTo' http://localhost:8080/TodoRestful/management/tasks/finalize
    {
        "id" : 1,
    }

### Response

#### 200 Ok
    HTTP/1.1 200 Ok
    Content-Type: application/json

#### 400 Bad Request   
    HTTP/1.1 400 Bad Request
    Status: 400 Bad Request
    Content-Type: application/json
    {
        "timestamp": "2021-01-25T02:15:03.562+0000",
        "status": 400,
        "error": "Bad Request",
        "message": "Task does not exists",
         "path": "/management/tasks/finalize"
    }
   
#### 401 Unauthorized
    HTTP/1.1 401 Unauthorized
    Status: 401 Unauthorized
    Content-Type: application/json
    {}
    
## Get All Tasks

### Request

`GET /management/tasks`

    curl -i -H 'Accept: application/json' -H 'token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW1vIiwicm9sZXMiOlt7ImlkIjoyLCJyb2xlTmFtZSI6IlVTRVIiLCJhdXRob3JpdHkiOiJVU0VSIn1dLCJpYXQiOjE2MTE1MjM0NzgsImV4cCI6MTYxMTUyNDA3OH0.PyF_HlYRaWBYh7dNdR0JOq0CVW1Bblpov6L7vNI_OTo' http://localhost:8080/TodoRestful/management/tasks

### Response

#### 200 Ok
    HTTP/1.1 200 Ok
    Content-Type: application/json
    [   
        {
            "id": 4,
            "name": "Do homework",
            "description": "Do english homework and send it in a email",
            "creationDate": "2021-01-25T02:36:31.539+0000",
            "expirationDate": "2021-01-29T13:12:51.536+0000",
            "owner": {
                "id": 6,
                "fullname": "Guillermo Madero Sanchez",
                "username": "madero",
                "lastModified": "2021-01-24T19:33:44.694+0000"
                },
            "type": {
                "id": 1,
                "name": "School",
                "creationDate": "2021-01-24T20:20:15.914+0000"
                },
            "status": false
        }
    ]
#### 401 Unauthorized
    HTTP/1.1 401 Unauthorized
    Status: 401 Unauthorized
    Content-Type: application/json
    {}