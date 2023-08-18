# Chatgpt-json-format-plugin

ChatGPT is an amazing tool that we use. When we ask it something, it gives us text in a way that's easy for people to read. However, when we want to connect it to another machine or server for automation, like AI stuff, it doesn't work well because it's not structured. So, I created a JSON plugin. This plugin gives us all the results in a special JSON format. Behind the scenes, it checks if the JSON is valid, and if not, it does some other things to make it right.

Introducing the ChatGPT JSON Plugin! It's designed to talk to ChatGPT and get a response in proper JSON format. There are two main ways to use it: you can send prompts, or you can use function calls. If you need JSON responses, the plugin checks if the response is already in JSON. If it's not, the plugin tries to change it into JSON format. The cool part is, even if it takes a few tries to talk to ChatGPT, the plugin keeps working until it gets a JSON response.

This feature is added to open source project [click](https://github.com/arakoodev/EdgeChains) on here to see open source project



## video

https://www.loom.com/share/631d5d1b025148f59493eb7ffbf42fe6?sid=be8c8992-0d75-4ecb-a4d8-4dd3b84b04dc

## Endpoints

### simple-chat Endpoint
Endpoint: GET http://localhost:8080/api/userStory

header:
```
Authorization : Your Api key
```

Request body format:
```
{
    "prompt": "your querry"
}
```
