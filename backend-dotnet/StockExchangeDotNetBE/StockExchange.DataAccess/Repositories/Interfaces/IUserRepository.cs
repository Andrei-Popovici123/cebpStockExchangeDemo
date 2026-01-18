using StockExchange.DataAccess.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Repositories.Interfaces
{
    public interface IUserRepository : IRepository<User>
    {
        Task<User> GetUserByUsername(string username);
    }
}
