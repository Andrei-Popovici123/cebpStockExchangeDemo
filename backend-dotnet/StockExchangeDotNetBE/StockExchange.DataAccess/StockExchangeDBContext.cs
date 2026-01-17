using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Microsoft.Identity.Client;
using StockExchange.DataAccess.Configurations;
using StockExchange.DataAccess.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.DataAccess
{
    public class StockExchangeDBContext : IdentityDbContext<User, IdentityRole<int>, int>
    {
        public StockExchangeDBContext(DbContextOptions<StockExchangeDBContext> options)
            : base(options)
        {
        }

        public DbSet<BankAccount> BankAccounts { get; set; }
        public DbSet<User> Users { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            
            new BankAccountConfiguration().Configure(modelBuilder.Entity<BankAccount>());
            new UserConfiguration().Configure(modelBuilder.Entity<User>());
        }
    }
}
