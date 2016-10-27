### couple curl commands to test REST API

#### get all user votes
`curl --user user1@votes.by:simpleuser "http://localhost:8080/rest/votes/getAll"`

#### get votes results
`curl --user user1@votes.by:simpleuser "http://localhost:8080/rest/votes/getVoteResults"`

#### get personal info
`curl --user user1@votes.by:simpleuser "http://localhost:8080/rest/profile"`

#### get restaurant by name with menu
`curl --user admin@votes.by:megaadmin "http://localhost:8080/rest/admin/restaurants/by?name=Семашко"`

#### get menu item with restaurant info
`curl --user admin@votes.by:megaadmin "http://localhost:8080/rest/admin/dishes/getWithRest?id=16&rest_id=6"`

#### delete users with id = 3
`curl --user admin@votes.by:megaadmin -X POST "http://localhost:8080/rest/admin/users/delete" --data "id=3"`