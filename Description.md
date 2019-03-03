# GraphQL

## Concepts

> GraphQL is a query language for your API, and a server-side runtime for executing queries by using a type system you define for your data. GraphQL isn't tied to any specific database or storage engine and is instead backed by your existing code and data.

### Queries

### Mutations

### Schemas and Types

## Example Queries and Mutations

### Queries

```
{
  appUsers {
    id
    name
    surname
    todos {
      id
      title
      description
    }
  }
}
```

### Mutations

```json
mutation WriteAppUser($input: AppUserInput) {
  writeAppUser(input: $input) {
    name
    surname
    todos {
      title
      description
    }
  }
}
```

```
{
  "input": {
    "name": "Max",
    "surname": "Mustermann",
    "todos": [
      {
          "title": "First",
          "description": "First TODO"
      },
      {
          "title": "Second",
          "description": "Second TODO"
      }
    ]
  }
}
```