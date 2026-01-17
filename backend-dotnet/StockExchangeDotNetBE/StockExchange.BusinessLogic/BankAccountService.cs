using StockExchange.BusinessLogic.Interfaces;
using StockExchange.DataAccess.DTOs;
using StockExchange.DataAccess.Models;
using StockExchange.DataAccess.Repositories.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace StockExchange.BusinessLogic
{
    public class BankAccountService : IBankAccountService
    {
        private readonly IBankAccountsRepository _bankAccountRepository;
       
        public BankAccountService(IBankAccountsRepository bankAccountRepository)
        {
            _bankAccountRepository = bankAccountRepository;
        }
        public async Task<BankAccountDto> CreateBankAccountAsync(int userId, string currency, decimal initialBalance)
        {
            var bankAccount = new BankAccount
            {
                userId = userId,
                currency = currency,
                balance = initialBalance
            };
            var createdBankAccount = await _bankAccountRepository.AddAsync(bankAccount);

            return new BankAccountDto
            {
                Id = createdBankAccount.Id,
                currency = createdBankAccount.currency,
                balance = createdBankAccount.balance
            };
        }

        public async Task DeleteBankAccountAsync(int id)
        {
            var bankAccount = await _bankAccountRepository.GetByIdAsync(id);

            if(bankAccount == null)
            {
                throw new Exception("Bank account not found");
            }

            await _bankAccountRepository.DeleteAsync(id);
        }

        public async Task<IEnumerable<BankAccountDto>> GetAllBankAccountsAsync()
        {
            var bankAccounts = await _bankAccountRepository.GetAllAsync();

            return bankAccounts.Select(ba => new BankAccountDto
            {
                Id = ba.Id,
                currency = ba.currency,
                balance = ba.balance
            });
        }

        public async Task<BankAccountDto> GetBankAccountByIdAsync(int id)
        {
            var bankAccount = await _bankAccountRepository.GetByIdAsync(id);
            
            if(bankAccount == null)
            {
                throw new Exception("Bank account not found");
            }

            return new BankAccountDto
            {
                Id = bankAccount.Id,
                currency = bankAccount.currency,
                balance = bankAccount.balance
            };
        }

        public async Task<IEnumerable<BankAccountDto>> GetBankAccountsByUserIdAsync(int userId)
        {
            var bankAccounts = await _bankAccountRepository.GetBankAccountsByUserId(userId);

            return bankAccounts.Select(ba => new BankAccountDto
            {
                Id = ba.Id,
                currency = ba.currency,
                balance = ba.balance
            });

        }

        public async Task<BankAccountDto> UpdateBankAccountBalanceAsync(int id, decimal newBalance)
        {
            var bankAccount = await _bankAccountRepository.GetByIdAsync(id);
            if (bankAccount == null)
            {
                throw new Exception("Bank account not found");
            }
            
            bankAccount.balance = newBalance;
            var updatedBankAccount = await _bankAccountRepository.UpdateAsync(bankAccount);
            
            return new BankAccountDto
            {
                Id = updatedBankAccount.Id,
                currency = updatedBankAccount.currency,
                balance = updatedBankAccount.balance
            };

        }
    }
}
