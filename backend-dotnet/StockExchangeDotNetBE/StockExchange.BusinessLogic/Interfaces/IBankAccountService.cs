using StockExchange.DataAccess.DTOs;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.BusinessLogic.Interfaces
{
    public interface IBankAccountService
    {
        Task<IEnumerable<BankAccountDto>> GetAllBankAccountsAsync();
        Task<BankAccountDto> GetBankAccountByIdAsync(int id);
        Task<BankAccountDto> CreateBankAccountAsync(int userId, string currency, decimal initialBalance);
        Task<BankAccountDto> UpdateBankAccountBalanceAsync(int id, decimal newBalance);
        Task DeleteBankAccountAsync(int id);
        Task<IEnumerable<BankAccountDto>> GetBankAccountsByUserIdAsync(int userId);
    }
}
