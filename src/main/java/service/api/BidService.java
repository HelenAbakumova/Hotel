package service.api;

import entity.Bid;

import java.util.List;

public interface BidService {
    int create(Bid bid);

    List<Bid> getAll();

    List<Bid> getBidsForUser(String email);

    void updateBid(String roomNumber, int bidId);

    Bid get(int id);

    int createUserBid(Bid bid);

    void delete(String bidNumber);
}
