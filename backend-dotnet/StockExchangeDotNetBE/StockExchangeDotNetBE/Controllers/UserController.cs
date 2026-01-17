using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using StockExchange.BusinessLogic.Interfaces;
using StockExchange.DataAccess.DTOs;

namespace StockExchangeDotNetBE.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private IUserService _userService;
        public UserController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpGet, Route("/api/users")]
        public async Task<IActionResult> GetAll()
        {
            var users = await _userService.GetAllUsersAsync();
            return Ok(users);
        }

        [HttpGet, Route("/api/users/{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var user = await _userService.GetUserByIdAsync(id);
            return Ok(user);
        }

        [HttpPut, Route("/api/users/{id}")]
        public async Task<IActionResult> UpdateUser(int id, [FromBody] UserUpdateDto userInfo)
        {
            var user = await _userService.UpdateUserAsync(id, userInfo);
            return Ok(user);
        }
    }
}
