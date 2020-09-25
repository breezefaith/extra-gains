import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  showError: boolean;
  username: string;
  password: string;

  constructor(
    private dataService: DataService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.showError = false;
    this.dataService.login(this.username, this.password).subscribe(
      (emp) => {
        if (emp != null) {
          const queryParams = {
            id: emp.id,
            login: emp.login,
            password: emp.password,
            firstName: emp.firstName,
            lastName: emp.lastName
          };
          this.router.navigate(["welcome"], { queryParams: queryParams, relativeTo: this.activatedRoute.parent })
        } else {
          this.showError = true;
        }
      },
      (err) => {
        alert("Server exception!");
      }
    );
  }
}
