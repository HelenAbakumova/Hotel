package repository.api;

import entity.Bid;

import java.sql.Connection;
import java.util.List;

public interface BidRepository {
    int createBid(Connection connection, Bid bid);

    List<Bid> getAllBids(Connection connection);

    void deleteBidById(Connection connection, int idBid);

    List<Bid> getBidForUser(String email, Connection connection);

    void updateBid(String roomNumber, int bidId, Connection connection);

    Bid get(Connection connection, int id);

    int createBidUser(Connection connection, Bid bid);
}
