using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.Models
{
    public class BankAccount
    {
        public int Id { get; set; }
        public string currency { get; set; } = string.Empty;
        public decimal balance { get; set; }
        public int userId { get; set; }

        public User User { get; set; } = null!;
    }
}
