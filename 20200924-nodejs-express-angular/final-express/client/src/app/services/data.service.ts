import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Employee } from '../entity/employee';

@Injectable()
export class DataService {
    private readonly loginUrl = "/api/login";
    constructor(
        private http: HttpClient,
    ) { }

    login(username: string, password: string): Observable<Employee> {
        const body = {
            username: username,
            password: password,
        };
        return this.http.post(this.loginUrl, body).pipe(
            switchMap(
                (res: any) => {
                    if (res == null) {
                        return of(null);
                    } else {
                        const emp = new Employee();
                        emp.id = res.id;
                        emp.login = res.login;
                        emp.password = res.password;
                        emp.firstName = res.firstName;
                        emp.lastName = res.lastName;

                        return of(emp);
                    }
                }
            )
        );
    }
}