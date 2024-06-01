package project.service;

import project.dao.WalletDao;
import project.dao.entity.Withdrawal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

public class WalletService {
    private final WalletDao walletDao;

    public WalletService() {
        this.walletDao = new WalletDao();
    }

    public BigDecimal getSuperAdminBalance(long userId) {
        return this.walletDao.getTotalSalesBySuperAdmin()
                .map(total -> {
                    BigDecimal totalWithdrawal = this.walletDao.getTotalWithdrawalByUserId(userId)
                            .orElse(BigDecimal.ZERO);
                    return total.subtract(totalWithdrawal);
                })
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAdminBalance(long userId) {
        return this.walletDao.getTotalSalesByAdmin(userId)
                .map(total -> {
                    BigDecimal totalWithdrawal = this.walletDao.getTotalWithdrawalByUserId(userId)
                            .orElse(BigDecimal.ZERO);
                    return total.subtract(totalWithdrawal);
                })
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public boolean withdraw(long userId, BigDecimal amount, Instant createdAt) {
        return this.walletDao.withdraw(userId, amount, createdAt);
    }

    public List<Withdrawal> getWithdrawalsByUserId(long userId) {
        return this.walletDao.getWithdrawalsByUserId(userId);
    }
}
