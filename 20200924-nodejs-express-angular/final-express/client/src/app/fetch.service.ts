import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

import { of } from "rxjs";
import { catchError, switchMap } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class FetchService {

  constructor(
    private http: HttpClient,
  ) { }

  get(url: string): Promise<string[]> {
    return this.http.get(url).pipe(
      switchMap(
        (l: string[]) => of(l)
      ),
      catchError(
        (err) => of([])
      )
    ).toPromise();
  }

}
