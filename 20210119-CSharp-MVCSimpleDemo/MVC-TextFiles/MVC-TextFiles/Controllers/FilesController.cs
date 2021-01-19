using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace MVC_TextFiles.Controllers
{
    public class FilesController : Controller
    {
        public IActionResult Index()
        {
            //string[] files = Directory.GetFiles("TextFiles");
            string[] files = new string[] { "text1.txt", "text2.txt", "text3.txt", "text4.txt", "text5.txt", "text6.txt" };
            ViewBag.FileList = files.ToList();
            return View(ViewBag);
        }

        public IActionResult Content()
        {
            var path = Request.Path;
            Console.WriteLine(path);
            return View();
        }
    }
}