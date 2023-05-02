# WORDY SCHEMA

## Player
#### (player\_id*, username, password, status, num\_wins, game\_id)

#### FOREIGN KEY status REFERENCES Player_Status NULLS NOT ALLOWED DELETE RESTRICT UPDATE CASCADE


#### FOREIGN KEY game\_id REFERENCES Game NULLS ALLOWED DELETE SET NULL UPDATE RESTRICT

## Game
#### (game\_id*, game_status, num\_rounds)


#### FOREIGN KEY status REFERENCES Game\_Status NULLS NOT ALLOWED DELETE RESTRICT UPDATE CASCADE

## Player\_Status
#### (status\_id*, status)


## Game\_Status
#### (status\_id*, status)

## Longest_Word
#### (word\_id*, word)

