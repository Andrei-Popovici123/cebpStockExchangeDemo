using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Models
{
    public class User : IdentityUser<int>
    {
        public bool Role { get; set; }
        public DateTime CreatedAt { get; set; }
        public ICollection<BankAccount> BankAccounts { get; set; } = new List<BankAccount>();
    }
}
