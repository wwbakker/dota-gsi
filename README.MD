# Dota-GSI

A quick hacky way to make ChatGPT your dota 2 coach.

Uses Dota Game-State-Integration to read the heroes of the current game you are spectating. 
Prompts the heroes to chatGPT to ask for item suggestions which counter the heroes on the enemy team.

## How to get this to work:
- Only works when spectating games.
- Player IDs are hardcoded in Suggestion.scala. (uncomment line 16 in Dota2Gsi to see the player ids in the current game you are watching)
- Place ChatGPT api key in a file with the path `~/.dota-gsi/chatgpt`
- Dota 2 needs to be configured like [this](https://github.com/MrBean355/dota2-gsi/wiki/Dota-2-Setup).

