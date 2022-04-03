package com.purang.manifest.domain.deliver;

import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.deliver.entity.dp.BusinessNoDP;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 出库清单领域测试
 */
public class DeliverManifestDomainTest {
    @Test
    public void testDeliverManifestBusinessNo() {
        BusinessNoDP firstBusinessNoDP = new BusinessNoDP().createNextBusinessNo();
        Assert.assertTrue("出库清单的单号不是以CKQD开头！", firstBusinessNoDP.toString().startsWith(BusinessNoDP.PREFIX));
        Assert.assertTrue("出库清单的单号长度不是16位！", firstBusinessNoDP.toString().length() == BusinessNoDP.REQUIRED_LENGTH);
        System.out.println(firstBusinessNoDP);

        BusinessNoDP nextBusinessNoDP = firstBusinessNoDP.createNextBusinessNo();
        System.out.println(nextBusinessNoDP);
    }

    @Test
    public void testTicketHolderCost() {
        DeliverManifestDO deliverManifestDO = new DeliverManifestDO();
        Ticket ticket1 = new Ticket();
        ticket1.setTicketAmount(new BigDecimal(200));
        ticket1.setLatestInStockTime("20211205162059");
        System.out.println(ticket1.computeHolderCost());
    }

    @Test
    public void testDeliverHolderCostTotal() {
        DeliverManifestDO deliverManifestDO = new DeliverManifestDO();
        Ticket ticket1 = new Ticket();
        ticket1.setTicketAmount(new BigDecimal(200));
        ticket1.setLatestInStockTime("20211205162059");

        Ticket ticket2 = new Ticket();
        ticket2.setTicketAmount(new BigDecimal(300));
        ticket2.setLatestInStockTime("20211206162059");

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        deliverManifestDO.setTicketList(tickets);
        BigDecimal holderCostTotal = deliverManifestDO.deliverCostAndIncomeTotal();
        System.out.printf("all tickets holder cost is : %s%n", holderCostTotal.stripTrailingZeros().toPlainString());
//        assertTrue(holderCostTotal.compareTo(new BigDecimal("1.2")) == 0);

    }
}
