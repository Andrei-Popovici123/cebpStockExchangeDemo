using StockExchange.DataAccess.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Repositories.Interfaces
{
    public interface IBankAccountsRepository : IRepository<BankAccount>
    {
        Task<IEnumerable<BankAccount>> GetBankAccountsByUserId(int userId);
    }
}
