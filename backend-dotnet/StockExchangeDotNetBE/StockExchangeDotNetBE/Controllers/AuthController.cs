using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using StockExchange.DataAccess.Models;

namespace StockExchangeDotNetBE.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AuthController : Controller
    {
        private readonly UserManager<User> _userManager;
        private readonly SignInManager<User> _signInManager;
        public AuthController(UserManager<User> userManager, SignInManager<User> signInManager)
        {
            _userManager = userManager;
            _signInManager = signInManager;
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register([FromBody] AuthDTO newUser)
        {
            var user = new User { UserName = newUser.UserName};
            var result = await _userManager.CreateAsync(user, newUser.Password);

            if (result.Succeeded) return Ok("User created successfully");
            return BadRequest(result.Errors);
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] AuthDTO user)
        {
            var result = await _signInManager.PasswordSignInAsync(user.UserName, user.Password, false, false);

            if (result.Succeeded) return Ok("Logged in");
            return Unauthorized("Invalid login attempt");
        }
    }
}
