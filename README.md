# Chatgpt-json-format-plugin
The ChatGPT JSON Plugin is made to talk to ChatGPT and get back a response in valid JSON format. 
It has two primary methods: using prompts and making function calls. 
If you need JSON responses, the plugin checks if the response is already in JSON or tries to convert it. 
If needed, the plugin makes repeated attempts to communicate with ChatGPT to ensure a JSON response format.
This feature is added to open source project [click](https://github.com/arakoodev/EdgeChains) on here to see open source project

## video

https://www.loom.com/share/22d020c7b7454cd1af4c2ace3ca977cc

## Endpoints
### Prompt-based Endpoint - This endpoint allows you to send a prompt and get a response from ChatGPT.

Endpoint: GET http://localhost:8080/api/extract

Request body format:
```
{
    "prompt": "Let's play poker. Your name is Tommy and you are a player in a game of No-Limit Texas Hold'em Poker. You have the cards Ac, Ah. The board is []. You have $100 in your stack. The pot is $20. You need to put $3 into the pot to play. The current bet is $3, and you are in seat 9 out of 9. Your position is in the Cutoff. You can call for $5, raise between $6 and $100, or fold for $0 What is the action you would like to take out of the following: ('call', 'raise', 'fold')?",
    "format": "{ action: { reason: string, type: string } amount: number }"
}
```
### Function Call Endpoint - This endpoint enables you to directly call a ChatGPT function and receive a response.

Endpoint: GET https://localhost:8080/api/function

Request body format:
```
{
    "prompt": "In a shallow wide bowl, whisk together the milk, cornstarch, ground flaxseeds, baking powder and vanilla. Add butter to a pan over medium-high heat and melt. Whisk the batter again right before dipping bread, as the cornstarch will settle to the bottom of the bowl. List all items used",
    "format": {"dependencies": {"type": "array", "items": {"type": "string"}}}
}
```
### Situation Handling Endpoint - This endpoint helps you handle situations with multiple prompts.

Endpoint: GET http://localhost:8080/api/situation

Request body format:
```
{
  "situation":"Let's play poker. Your name is Tommy and you are a player in a game of No-Limit Texas Hold'em Poker.You have the cards Ac, Ah. The board is []. You have $100 in your stack.The pot is $20. You need to put $3 into the pot to play.The current bet is $3, and you are in seat 9 out of 9.Your position is in the Cutoff.",
  "validAction":"You can call for $5, raise between $6 and $100, or fold for $0",
  "callToAction":"What is the action you would like to take out of the following: ('call', 'raise', 'fold')?",
  "actionFormat":" Specify the amount associated with that action in the format:{ action: { reason: string, type: string} amount: number } Only return values in this format (no other text is necessary)"
}
```

