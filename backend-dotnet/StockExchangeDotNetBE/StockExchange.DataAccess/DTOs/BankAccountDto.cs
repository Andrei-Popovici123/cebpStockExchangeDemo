using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.DTOs
{
    public class BankAccountDto
    {
        public int Id { get; set; }
        public string currency { get; set; } = string.Empty;
        public decimal balance { get; set; }
    }
}
