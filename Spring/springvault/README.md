# Spring Vault MVP

## Install Vault
* Download Vault : [https://www.vaultproject.io/downloads.html](https://www.vaultproject.io/downloads.html)
* Add ***vault.exe*** to Windows ***Env. Variables.***

## Development Configuration
Configure Vault **dev Environment** by setting the following variables
```
set VAULT_TOKEN=00000000-0000-0000-0000-000000000000
set VAULT_ADDR=http://127.0.0.1:8200
```
Run the Vault Server
```
vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
```

## Basic Operations
More infos: [Here](https://medium.com/@Ankitthakur/spring-boot-spring-vault-e9e973a17036)
#### Insert
```
vault kv put secret/my-secret my-value=springvault
```
#### Read
```
vault kv get secret/my-secret
```
#### Update
> There is no way to update the value of current version. If value will get updated, so will the version.
```
vault kv put -cas=1 secret/my-secret my-value=itsasecret
```
> NOTE: If -cas=0 the write will only be performed if the key doesn’t exist. 
> If the index is non-zero the write will only be allowed if the key’s current version 
> matches the version specified in the cas parameter.
#### Delete
> **vault kv delete** performs a **soft deletion** that marks a version as deleted 
> and creates a deletion_time timestamp. 
>Data removed with vault kv delete **can be un-deleted** by using vault kv undelete
```
vault kv delete secret/my-secret
```
```
vault kv destroy secret/my-secret
```

## Demo
```
vault kv put secret/springvault springvault.username=demouser springvault.password=springvault springvault.url=notyetset
```
Write Username, Password and Url
```
vault kv put secret/springvault/postgresql springvault.username=springvaultuser springvault.password=springvaultpassword springvault.url="jdbc:postgresql://localhost:5432/springvaultdatabase"
```
Read Username Password and Url values
```
vault kv get secret/springvault/postgresql
```