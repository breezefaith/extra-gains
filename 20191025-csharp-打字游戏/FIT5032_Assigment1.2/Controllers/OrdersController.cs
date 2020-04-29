using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using FIT5032_Assigment1._2.Models;
using Microsoft.AspNet.Identity;

namespace FIT5032_Assigment1._2.Controllers
{
    public class OrdersController : Controller
    {
        private CarRental db = new CarRental();

        // GET: Orders
        public ActionResult Index()
        {
            var userId = User.Identity.GetUserId();
            var orders = db.Orders.Where(r => r.CustomerId == userId);
            return View(orders.ToList());
        }

        public ActionResult SelAgency()
        {
            var travelAgencies = db.TravelAgencies.Include(t => t.CarRentalCompany);
            return View(travelAgencies.ToList());
        }

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

        // GET: Orders/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Order order = db.Orders.Find(id);
            if (order == null)
            {
                return HttpNotFound();
            }
            return View(order);
        }

        // GET: Orders/Create
        public ActionResult Create(int id)
        {
            var userId = User.Identity.GetUserId();
            ViewBag.UserId = userId;
            ViewBag.AgencyId = id;
            return View();
        }

        // POST: Orders/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,AgencyId,CustomerId,CarType,PickupDate,ReturnDate,PickupLocation,ReturnLocation")] Order order)
        {
            if (ModelState.IsValid)
            {
                db.Orders.Add(order);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.AgencyId = new SelectList(db.TravelAgencies, "Id", "Name", order.AgencyId);
            return View(order);
        }

        // GET: Orders/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Order order = db.Orders.Find(id);
            if (order == null)
            {
                return HttpNotFound();
            }
            ViewBag.AgencyId = new SelectList(db.TravelAgencies, "Id", "Name", order.AgencyId);
            return View(order);
        }

        // POST: Orders/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,AgencyId,CustomerId,CarType,PickupDate,ReturnDate,PickupLocation,ReturnLocation")] Order order)
        {
            if (ModelState.IsValid)
            {
                db.Entry(order).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.AgencyId = new SelectList(db.TravelAgencies, "Id", "Name", order.AgencyId);
            return View(order);
        }

        // GET: Orders/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Order order = db.Orders.Find(id);
            if (order == null)
            {
                return HttpNotFound();
            }
            return View(order);
        }

        // POST: Orders/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Order order = db.Orders.Find(id);
            db.Orders.Remove(order);
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
