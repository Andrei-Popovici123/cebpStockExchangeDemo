using Microsoft.EntityFrameworkCore;
using StockExchange.DataAccess.Models;
using StockExchange.DataAccess.Repositories.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Repositories
{
    public class UserRepository : BaseRepository<User>, IUserRepository
    {
        private readonly StockExchangeDBContext _context;
        public UserRepository(StockExchangeDBContext context) : base(context)
        {
            _context = context;
        }

        public async Task<User> GetUserByUsername(string username)
        {
            var user = await _context.Users
                .FirstOrDefaultAsync(u => u.UserName == username);

            if (user == null)
            {
                throw new KeyNotFoundException($"User with username '{username}' not found.");
            }

            return user;
        }
    }
}
