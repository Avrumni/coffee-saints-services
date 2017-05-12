# Coffee Saints - Services

## Prerequisites

* serverless

```npm install -g serverless```

* scala

```brew install sbt```

* aws credentials - [see here](https://github.com/serverless/serverless/blob/master/docs/providers/aws/guide/credentials.md)


## Build

```sbt assembly```

## Deploy

```serverless deploy```

## Usage

### Create

```
curl -X POST https://XXXX.execute-api.region.amazonaws.com/dev/todos --data '{ "body" : "Learn Serverless" }'
```

### Read all


```
curl https://XXXX.execute-api.region.amazonaws.com/dev/todos
```

### Read one

```
curl https://XXXX.execute-api.region.amazonaws.com/dev/todos/<id>
```

### Update

```
curl -X PUT https://XXXX.execute-api.region.amazonaws.com/dev/todos/<id> --data '{ "body" : "Understand Serverless" }'
```

### DELETE

```
curl -X DELETE https://XXXX.execute-api.region.amazonaws.com/dev/todos/<id>
```
