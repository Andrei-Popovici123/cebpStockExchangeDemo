using Microsoft.EntityFrameworkCore;
using StockExchange.DataAccess.Models;
using StockExchange.DataAccess.Repositories.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Repositories
{
    public class BankAccountsRepository : BaseRepository<BankAccount>, IBankAccountsRepository
    {
        private readonly StockExchangeDBContext _context;
        public BankAccountsRepository(StockExchangeDBContext context) : base(context)
        {
            _context = context;
        }
        public async Task<IEnumerable<BankAccount>> GetBankAccountsByUserId(int userId)
        {
            return await _context.BankAccounts
                .Where(ba => ba.userId == userId)
                .ToListAsync();
        }
    }
}
