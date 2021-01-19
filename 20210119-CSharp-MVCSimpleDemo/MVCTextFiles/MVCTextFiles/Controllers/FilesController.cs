using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace MVCTextFiles.Controllers
{
    public class FilesController : Controller
    {
        // GET: Files
        public ActionResult Index()
        {
            string[] files = Directory.GetFiles(Server.HtmlEncode(Request.PhysicalApplicationPath) + Path.DirectorySeparatorChar + "TextFiles");
            var sl = new List<string>();
            foreach (string f in files)
            {
                sl.Add(f.Substring(f.LastIndexOf(Path.DirectorySeparatorChar) + 1));
            }
            ViewBag.FileList = sl;
            return View(ViewBag);
        }

        public ActionResult Content()
        {
            var fileName = RouteData.Values["id"];
            var filePath = Server.HtmlEncode(Request.PhysicalApplicationPath) + Path.DirectorySeparatorChar + "TextFiles" + Path.DirectorySeparatorChar + fileName + ".txt";

            StreamReader sr = new StreamReader(filePath);
            var content = "";
            while (!sr.EndOfStream)
            {
                string line = sr.ReadLine();
                content += line;
            }
            sr.Close();
            ViewBag.textContent = content;
            return View();
        }
    }
}