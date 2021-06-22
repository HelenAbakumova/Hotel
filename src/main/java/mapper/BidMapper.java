package mapper;

import entity.Bid;
import entity.dto.BidDto;

public class BidMapper {



    public BidDto entityToDto(Bid bid, String email) {
        return new BidDto(bid.getId(), email, bid.getCapacity(), bid.getCategory(), bid.getArrival(), bid.getDeparture());
    }
}
