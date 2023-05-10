package server.database;


import com.mysql.cj.x.protobuf.MysqlxPrepare;
import server.GameServantImpl;
import wordy_idl.NoGameFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The DatabaseBridge is a class meant to be used by the server in initiating a connection to the
 * MySQL database to be used for the Wordy backend. Everything that the server needs to
 * access in the database should be provided here using methods.
 */
public class DatabaseBridge {

    private static Connection connection;

    public DatabaseBridge() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/wordy", "root", "");
    }


    public int[] getGamePlayers(int gameId) {
        String query = "SELECT player_id FROM player WHERE game_id = " + gameId;
        ArrayList<Integer> playerIDs = new ArrayList<>();

        try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.beforeFirst();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                playerIDs.add(playerId);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN getGamePlayers");
        }

        int[] idArray = new int[playerIDs.size()];
        for (int i = 0; i < playerIDs.size(); i++)
            idArray[i] = playerIDs.get(i);
        return idArray;
    }

    public int createNewGame(int playerId) {
        String query = "INSERT INTO game(game_status, num_rounds) VALUES(?, ?)";
        int gameId = -1;

        try(PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, getEquivalentGameStatus(GameServantImpl.GAME_STATUS.PENDING));
            preparedStatement.setInt(2, 0);

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            gameId = resultSet.getInt(1);
            addPlayerToGame(gameId, playerId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN createNewGame");
        }
        return gameId;
    }

    public int findPendingGame(int playerId) throws NoGameFoundException {
        String query = "SELECT game_id FROM game WHERE game_status = " + getEquivalentGameStatus(
                GameServantImpl.GAME_STATUS.PENDING) + " LIMIT 1";
        int gameId = 0;


        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            if (resultSet.next()) {
                gameId = resultSet.getInt("game_id");
                addPlayerToGame(gameId, playerId);
            }
            else throw new NoGameFoundException();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED FOR findPendingGame");
        }

        return gameId;
    }

    private void addPlayerToGame(int gameId, int playerId) {
        String query = "UPDATE player SET game_id = ? WHERE player_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, playerId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN addPlayerToGame");
        }
    }

    public int getEquivalentGameStatus(GameServantImpl.GAME_STATUS status) {
        String stringStatus = status.getStatus();
        int equivalentStatus = 0;

        String query = String.format("SELECT status_id FROM game_status WHERE status LIKE \'%s\'", stringStatus);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) equivalentStatus = resultSet.getInt("status_id");
            else throw new SQLException();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN getEquivalentGameStatus");
        }

        return equivalentStatus;
    }

    public String[] getUserData(String username) {
        String[] queryResult = null;
        String query = "SELECT * FROM player WHERE username = ?";
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.beforeFirst();
            if (resultSet.next()) {
                String player_id = resultSet.getString("player_id");
                String queriedUsername = resultSet.getString("username");
                String queriedPassword = resultSet.getString("password");
                String status = resultSet.getString("status");
                String num_wins = resultSet.getString("num_wins");
                String game_id = resultSet.getString("game_id");
                queryResult = new String[]{
                    player_id, queriedUsername, queriedPassword, status, num_wins, game_id
                };
            }

            System.out.println("QUERY SUCCESS");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED");
        }

        return queryResult;
    }


    public int getEquivalentPlayerStatus(String status) {

        String query = String.format("SELECT status_id FROM player_status WHERE LOWER(status) LIKE \"%s\"",
        status.toLowerCase());
        int statusCode = 0;
        try (Statement statement =
                     connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.beforeFirst();
            if (!resultSet.next()) throw new SQLException();
            else statusCode = resultSet.getInt("status_id");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return statusCode;

    }

    public String[][] getTopPlayers(int limit) {
        String query = String.format("SELECT * FROM best_player ORDER BY WINS DESC LIMIT %d", limit);
        ArrayList<String[]> topPlayers = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            String player = resultSet.getString("Player");
            String wins = resultSet.getString("Wins");
            topPlayers.add(new String[] {player, wins});

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topPlayers.toArray(new String[0][]);
    }

    public String[] getLongestWords(int limit) {
        String query = String.format("SELECT word FROM longest_word ORDER BY LENGTH(word) DESC LIMIT %d", limit);

        try (Statement statement =
                     connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet result = statement.executeQuery(query);
            List<String> longestWords = new ArrayList<>();

            while (result.next()) {
                String word = result.getString("word");
                longestWords.add(word);
            }

            return longestWords.toArray(new String[0]);

        } catch (SQLException e) {
            System.out.println("Error while executing the query: " + query);
            e.printStackTrace();
            return new String[0];
        }
    }

    public void insert(String table, String[] tuples, String[] values) throws SQLException {
        String tuple = "";
        String value = "";

        for (String col : tuples) {
            tuple += col + ", ";
        }
        for (int x = 0; x < tuples.length; x++) {
            value += "?, ";
        }
        tuple = tuple.substring(0, tuple.length() - 2);
        value = value.substring(0, value.length() - 2);

        String query = "INSERT INTO " + table + " (" + tuple + ") VALUES (" + value + ");";

        PreparedStatement prep = connection.prepareStatement(query);

        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                prep.setNull(i + 1, Types.NULL);
            } else {
                prep.setObject(i + 1, values[i]);
            }
        }

        prep.executeUpdate();
        prep.close();
    }

    public void update(String table, String[] tuples, String[] values, String whereArg) throws SQLException {
        String args = "";

        for (int x = 0; x < tuples.length; x++) {
            args += tuples[x] + " = ?, ";
        }
        args = args.substring(0, args.length() - 2);

        String query = "UPDATE " + table + " SET " + args + " WHERE " + whereArg + ");";

        PreparedStatement prep = connection.prepareStatement(query);

        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                prep.setNull(i + 1, Types.NULL);
            } else {
                prep.setObject(i + 1, values[i]);
            }
        }

        prep.executeUpdate();
        prep.close();
    }


    public int getPlayerCount(int gameId) {
        String query = "SELECT player_id FROM player WHERE game_id = " + gameId;
        int players = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()) players++;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN getPlayerCount");
        }

        return players;
    }

    public void setGameStatus(int gameId, GameServantImpl.GAME_STATUS status) {
        String query = String.format("UPDATE game SET game_status = %d WHERE game_id =  %d",
                getEquivalentGameStatus(status), gameId);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN setGameStatus");
        }
    }

    public int getGameStatus(int gameId) {
        String query = String.format("SELECT game_status FROM game WHERE game_id = %d", gameId);
        int gameStatus = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            gameStatus = resultSet.getInt("game_status");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN getGameStatus");
        }

        return gameStatus;
    }

    public void deleteGame(int gameId) {
        String query = String.format("DELETE FROM game WHERE game_id = %d", gameId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("QUERY FAILED IN deleteGame");
        }
    }

}
