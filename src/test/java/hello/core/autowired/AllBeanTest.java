package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice1 = discountService.discount(member, 5000, "fixDiscountPolicy");
        assertThat(discountPrice1).isEqualTo(1000);

        int discountPrice2 = discountService.discount(member, 30000, "rateDiscountPolicy");
        assertThat(discountPrice2).isEqualTo(3000);


    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> polices;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.polices = polices;
            System.out.println("policyMap = " + policyMap);
            System.out.println("polices = " + polices);
        }

        public int discount(Member member, int price, String discountCode) {
            return policyMap.get(discountCode).discount(member, price);
        }
    }

}
