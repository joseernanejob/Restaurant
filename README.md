# Projeto Restaurante

## Descrição

O Projeto é uma solução para simplificar e modernizar o 
processo de pedidos em estabelecimentos comerciais, 
substituindo as tradicionais comandas físicas por 
uma versão digital acessível através de uma API Rest. 
Este projeto visa proporcionar uma experiência mais 
eficiente e conveniente tanto para os clientes quanto 
para os estabelecimentos, permitindo que os pedidos sejam 
feitos de forma rápida e precisa.

## Endpoints:

## Categorias

### Post - /category

- Rota para criação de categorias.
- Não e possível realizar o cadastro de duas categorias com o mesmo nome.
- Request Body:
    - **name**: string, máximo de 50 caracteres.

``` 
{
    "name": "Sobremesa"
}
```

- Return success [201 CREATED]
```
{
    "id": "1",
    "name": "Sobremesa"
}
```
- Return name already registered [400 BAD_REQUEST]
```
{
    "timeStamp": "2024-01-30T15:51:15.895+00:00",
    "message": "Category name already registered! ",
    "details": "uri=/category"
}
```

### Put - /category

- Rota para atualizar os dados de uma categoria.
- Não e possível realizar o cadastro de duas categorias com o mesmo nome.
- Request Body:
    - **id**: long;
    - **name**: string, máximo de 50 caracteres.

``` 
{
    "id": "1",
    "name": "Bebida"
}
```

- Return success [200 OK]
```
{
    "id": "1",
    "name": "Bebida"
}
```
- 
- Return Not Found [200 OK]
```
{
    "timeStamp": "2024-01-30T15:57:23.940+00:00",
    "message": "Category is not found!",
    "details": "uri=/category"
}
```

- Return name already registered [400 BAD_REQUEST]
```
{
    "timeStamp": "2024-01-30T15:51:15.895+00:00",
    "message": "Category name already registered! ",
    "details": "uri=/category"
}
```

### Get - /category

- Rota para buscar todas as categorias.
- Return success [200 OK]
```
[
    {
        "id": "1",
        "name": "Bebida"
    }
]
```

### Get - /category/{id}

- Rota para buscar uma categoria especifica.
- Return success [200 OK]
```
{
    "id": "1",
    "name": "Bebida"
}
```

### Delete - /category/{id}

- Rota para apagar uma categoria especifica.
- Return success [204 NO_CONTENT]


## Produtos

### Post - /product

- Rota para criação de produtos.
- Request Body:
    - **name**: string, máximo de 50 caracteres;
    - **description**: string;
    - **category**: Category.

``` 
{
    "name": "Refrigerante Generico",
    "description": "refrigerante de laranja",
    "category": {
        "id": 1,
        "name": "Bebida"
    }
}
```

- Return success [201 CREATED]
```
{
    "id": 1,
    "name": "Refrigerante Generico",
    "description": "refrigerante de laranja",
    "category": {
        "id": 1,
        "name": "Bebida"
    }
}
```

### Put - /product

- Rota para atualizar os dados de uma categoria.
- Request Body:
    - **id**: long;
    - **name**: string, máximo de 50 caracteres;
    - **description**: string;
    - **category**: Category.

``` 
{
    "id": 1,
    "name": "Refrigerante Generico",
    "description": "refrigerante de Uva",
    "category": {
        "id": 1,
        "name": "Bebida"
    }
}
```

- Return success [200 OK]
```
{
    "id": 1,
    "name": "Refrigerante Generico",
    "description": "refrigerante de Uva",
    "category": {
        "id": 1,
        "name": "Bebida"
    }
}
```

### Get - /product

- Rota para buscar todos os produtos.
- Return success [200 OK]
```
[
    {
        "id": 1,
        "name": "Refrigerante Generico",
        "description": "refrigerante de Uva",
        "category": {
            "id": 1,
            "name": "Bebida"
        }
    }
]
```

### Get - /product/category/{id}

- Rota para buscar todos os produtos de uma categoria especifica.
- Return success [200 OK]
```
[
    {
        "id": 1,
        "name": "Refrigerante Generico",
        "description": "refrigerante de Uva",
        "category": {
            "id": 1,
            "name": "Bebida"
        }
    }
]
```

### Get - /product/{id}

- Rota para buscar um produto especifico.
- Return success [200 OK]
```
{
    "id": 1,
    "name": "Refrigerante Generico",
    "description": "refrigerante de Uva",
    "category": {
        "id": 1,
        "name": "Bebida"
    }
}
```

### Delete - /produto/{id}

- Rota para apagar um produto especifico.
- Return success [204 NO_CONTENT]



# PROJETO AINDA EM DESENVOLVIMENTO.
# AGUARDE PARA MAIS INFORMAÇÕES.