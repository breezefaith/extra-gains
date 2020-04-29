using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using FIT5032_Assigment1._2.Models;

namespace FIT5032_Assigment1._2.Controllers
{
    public class TravelAgenciesController : Controller
    {
        private CarRental db = new CarRental();

        // GET: TravelAgencies
        public ActionResult Index()
        {
            var travelAgencies = db.TravelAgencies.Include(t => t.CarRentalCompany);
            return View(travelAgencies.ToList());
        }

        // GET: TravelAgencies/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TravelAgency travelAgency = db.TravelAgencies.Find(id);
            if (travelAgency == null)
            {
                return HttpNotFound();
            }
            return View(travelAgency);
        }

        // GET: TravelAgencies/Create
        public ActionResult Create()
        {
            ViewBag.RentalId = new SelectList(db.CarRentalCompanies, "Id", "CarType");
            return View();
        }

        // POST: TravelAgencies/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Name,Location,StartDate,RentalId")] TravelAgency travelAgency)
        {
            if (ModelState.IsValid)
            {
                db.TravelAgencies.Add(travelAgency);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.RentalId = new SelectList(db.CarRentalCompanies, "Id", "CarType", travelAgency.RentalId);
            return View(travelAgency);
        }

        // GET: TravelAgencies/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TravelAgency travelAgency = db.TravelAgencies.Find(id);
            if (travelAgency == null)
            {
                return HttpNotFound();
            }
            ViewBag.RentalId = new SelectList(db.CarRentalCompanies, "Id", "CarType", travelAgency.RentalId);
            return View(travelAgency);
        }

        // POST: TravelAgencies/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Name,Location,StartDate,RentalId")] TravelAgency travelAgency)
        {
            if (ModelState.IsValid)
            {
                db.Entry(travelAgency).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.RentalId = new SelectList(db.CarRentalCompanies, "Id", "CarType", travelAgency.RentalId);
            return View(travelAgency);
        }

        // GET: TravelAgencies/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TravelAgency travelAgency = db.TravelAgencies.Find(id);
            if (travelAgency == null)
            {
                return HttpNotFound();
            }
            return View(travelAgency);
        }

        // POST: TravelAgencies/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            TravelAgency travelAgency = db.TravelAgencies.Find(id);
            db.TravelAgencies.Remove(travelAgency);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
