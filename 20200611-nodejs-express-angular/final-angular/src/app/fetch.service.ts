import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FetchService {

  constructor() { }

  get(url: string): Promise<string[]> { return []; }

}
