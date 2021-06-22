package service;

import db.JDBCManager;
import entity.Bid;
import entity.Room;
import repository.BidRepositoryImpl;
import service.api.BidService;
import service.api.RoomService;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BidServiceImpl implements BidService {
    BidRepositoryImpl bidRepository;
    RoomService roomService;
    JDBCManager jdbcManager;

    public BidServiceImpl(BidRepositoryImpl bidRepository, RoomService roomService, JDBCManager jdbcManager) {
        this.bidRepository = bidRepository;

        this.roomService = roomService;
        this.jdbcManager = jdbcManager;
    }

    public int create(Bid bid) {

        if (bid.getCategory() == null) {
            Room room = roomService.getRoom(bid.getRoomId());
            bid.setCategory(room.getCategory());
            bid.setCapacity(room.getCapacity());
        }

        return jdbcManager.doInTransaction(connection -> {
            try {
                return bidRepository.createBid(connection, bid);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }


    @Override
    public List<Bid> getAll() {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return bidRepository.getAllBids(connection);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public List<Bid> getBidsForUser(String email) {

        return jdbcManager.doInTransaction(connection -> {
            try {
                List<Bid> bids = bidRepository.getBidForUser(email, connection);
                bids.forEach(bid -> {
                    int price = roomService.getRoom(bid.getRoomId()).getPrice();
                    long diffInMillies = Math.abs(bid.getDeparture().getTime() - bid.getArrival().getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    bid.setTotalPrice(Math.toIntExact(diff * price));
                });
                return bids;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public void updateBid(String roomNumber, int bidId) {

        jdbcManager.doInTransaction(connection -> {
            try {
                bidRepository.updateBid(roomNumber, bidId, connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public Bid get(int id) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return bidRepository.get(connection, id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public int createUserBid(Bid bid) {
        return jdbcManager.doInTransaction(connection -> {
            try {
                return bidRepository.createBidUser(connection, bid);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public void delete(String bidNumber) {
        jdbcManager.doInTransaction(connection -> {
            try {
                bidRepository.deleteBidById(connection, Integer.parseInt(bidNumber));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}

