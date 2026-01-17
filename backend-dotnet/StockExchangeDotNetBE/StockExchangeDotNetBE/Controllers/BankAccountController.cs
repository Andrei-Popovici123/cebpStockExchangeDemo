using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using StockExchange.BusinessLogic.Interfaces;

namespace StockExchangeDotNetBE.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class BankAccountController : ControllerBase
    {
        private IBankAccountService _bankAccountService;
        public BankAccountController(IBankAccountService bankAccountService)
        {
            _bankAccountService = bankAccountService;
        }

        [HttpPost, Route("/api/bankaccounts")]
        public async Task<IActionResult> CreateBankAccount(int userId, string currency, decimal initialBalance)
        {
            var bankAccount = await _bankAccountService.CreateBankAccountAsync(userId, currency, initialBalance);
            return Ok(bankAccount);
        }

        [HttpDelete, Route("/api/bankaccounts/{id}")]
        public async Task<IActionResult> DeleteBankAccount(int id)
        {
            await _bankAccountService.DeleteBankAccountAsync(id);
            return Ok();
        }

        [HttpGet, Route("/api/bankaccounts")]
        public async Task<IActionResult> GetAllBankAccounts()
        {
            var bankAccounts = await _bankAccountService.GetAllBankAccountsAsync();
            return Ok(bankAccounts);
        }

        [HttpGet, Route("/api/bankaccounts/{id}")]
        public async Task<IActionResult> GetBankAccountById(int id)
        {
            var bankAccount = await _bankAccountService.GetBankAccountByIdAsync(id);
            return Ok(bankAccount);
        }

        [HttpPut, Route("/api/bankaccounts/{id}/{balance}")]
        public async Task<IActionResult> UpdateBankAccountBalance(int id, decimal balance)
        {
            var bankAccount = await _bankAccountService.UpdateBankAccountBalanceAsync(id, balance);
            return Ok(bankAccount);
        }

        [HttpGet, Route("/api/users/{userId}/bankaccounts")]
        public async Task<IActionResult> GetBankAccountsByUserId(int userId)
        {
            var bankAccounts = await _bankAccountService.GetBankAccountsByUserIdAsync(userId);
            return Ok(bankAccounts);
        }
    }
}
