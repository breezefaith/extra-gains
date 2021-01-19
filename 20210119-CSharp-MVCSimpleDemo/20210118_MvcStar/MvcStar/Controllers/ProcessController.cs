using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace MvcStar.Controllers
{
    public class ProcessController : Controller
    {
        public IActionResult Index()
        {
            ViewBag.Processes = System.Diagnostics.Process.GetProcesses();
             
            return View();
        }

        public IActionResult Concrete()
        {
            var processes = System.Diagnostics.Process.GetProcesses();
             
            return View(processes);
        }

        public IActionResult Display(int id)
        {
            var theProcess = System.Diagnostics.Process.GetProcessById(id);
             
            return View(theProcess);
        }
    }
}