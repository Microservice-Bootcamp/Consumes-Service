package com.rs.consumes.service;

import com.rs.consumes.dto.Account;
import com.rs.consumes.dto.BankAccount;
import com.rs.consumes.dto.DebitCard;
import com.rs.consumes.entity.Payment;
import com.rs.consumes.repository.PaymentRepository;
import com.rs.consumes.util.WebClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WebClientTemplate webClientTemplate;

    public Mono<Payment> paymentWithCard(Payment payment){
        var listAccountNumber = getCard(payment.getCreditCardNumber())
                .map(account -> account.getBankAccounts().stream().map(Account::getAccountNumber).collect(Collectors.toList()));

        //var accountQuantity = listAccountNumber.map(List::size);

        var bankAccounts = listAccountNumber.flatMapMany(value -> {
            var streamAccounts = value.stream();
            return Flux.fromStream(streamAccounts);
        });

        var listBankAccounts =  bankAccounts.flatMap(this::getBankAccount);

        var accountToDiscount = listBankAccounts.filter(account ->account.getBalance()>=payment.getAmount()).distinct().next();

        /*var accountToDiscount = listBankAccounts.toStream()
                .filter(account ->account.getBalance()>=payment.getAmount())
                .distinct()
                .findFirst().orElse(new BankAccount());
        */
        return accountToDiscount.flatMap(account ->{
            if(account.getBalance() != null && payment.getAmount()>0 && account.getBalance()>=payment.getAmount()){
                account.setBalance((int) (account.getBalance()-payment.getAmount()));
                return updateBalanceOfAccountBank(account).then(paymentRepository.save(payment));
            }
            payment.setAmount(0.0);
            return paymentRepository.save(payment);
        });

        /*if(accountToDiscount.getBalance() !=null && payment.getAmount()>0){
            accountToDiscount.setBalance((int) (accountToDiscount.getBalance()-payment.getAmount()));
            return updateBalanceOfAccountBank(accountToDiscount)
                    .then(paymentRepository.save(payment));
        }*
        //var accountSufficientBalance = listBankAccounts.flatMap()
        //var totalBalance = listBankAccounts.map(BankAccount::getBalance).reduce(0, Integer::sum);
        /*var operation = listBankAccounts
                .flatMap(account ->{
                    if( account.getBalance()>= payment.getAmount()){
                        //account.setBalance((int) (account.getBalance() -payment.getAmount()));
                        //return updateBalanceOfAccountBank(account).
                        System.out.println("se esta haciendo ejecucion");
                        return Mono.empty();

                    }
                    System.out.println("fuera");

                    return Mono.empty();
                });*/

        /*bankAccounts.subscribe(System.out::println);
        totalBalance.subscribe(l -> System.out.println("ttotal balance = " +l));
        listBankAccounts.subscribe(System.out::println);

        operation.subscribe(System.out::println);*/

        //return Mono.empty();
    }


    private Mono<DebitCard> getCard(String cardNumber){
        return webClientTemplate.templateWebClient("http://localhost:8081")
                .get()
                .uri("/debit-card/status/"+cardNumber)
                .retrieve()
                .bodyToMono(DebitCard.class);
    }

    private Mono<BankAccount> getBankAccount(Integer accountNumber){
        return webClientTemplate.templateWebClient("http://localhost:8081")
                .get()
                .uri("/account/detail/"+accountNumber)
                .retrieve()
                .bodyToMono(BankAccount.class);
    }

    private Mono<BankAccount> updateBalanceOfAccountBank(BankAccount bankAccount){
        return webClientTemplate.templateWebClient("http://localhost:8081")
                .put()
                .uri("/account/update")
                .body( Mono.just(bankAccount), BankAccount.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BankAccount.class);
    }

    private double MyPay(Integer pagado){
        var aPagar = 22;
        aPagar = aPagar -pagado;
        return aPagar;
    }

}
