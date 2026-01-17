using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess.DTOs
{
    public class UserDto
    {
        public int Id { get; set; }
        public string UserName { get; set; } = string.Empty;
        public bool Role { get; set; }
        public DateTime CreatedAt { get; set; }
    }
}
