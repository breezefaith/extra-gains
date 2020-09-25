import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../entity/employee';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  emp: Employee;
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParamMap.subscribe(
      (paramMap) => {
        let emp = new Employee();

        emp.id = paramMap.get("id");
        emp.login = paramMap.get("login");
        emp.password = paramMap.get("password");
        emp.firstName = paramMap.get("firstName");
        emp.lastName = paramMap.get("lastName");

        this.emp = emp;
      }
    );
  }

}
