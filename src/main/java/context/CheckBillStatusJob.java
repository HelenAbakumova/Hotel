package context;

import entity.Bill;
import entity.BillStatus;
import service.api.BillService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckBillStatusJob implements Runnable {
    BillService billService;
    //TODO service for new table status for rooms

    public CheckBillStatusJob(BillService billService) {
        this.billService = billService;
    }

    @Override
    public void run() {
        List<Bill> bills = billService.get(BillStatus.UNPAID);
        bills.forEach(bill -> {

            long diffInMillis = Math.abs(new Date(System.currentTimeMillis()).getTime() - bill.getBillDate().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

            if (diff > 2){
                //todo change room status from booked to free
        }
        });
    }
}
