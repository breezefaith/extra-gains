import { Injectable } from "@angular/core";
import { Movie } from '../entity/movie';
import { LOCAL_STORAGE_KEY } from '../constant';

@Injectable()
export class MovieService {
    constructor() {

    }

    getAll(): Movie[] {
        const list: Movie[] = [];
        const data = this.getMoviesByStorage();
        for (const name in data) {
            list.push(data[name]);
        }
        return list;
    }

    queryByName(name: string) {
        return this.getAll().filter(
            (item) => {
                return item.name.indexOf(name) != -1;
            }
        )
    }

    getDetailByName(name: string): Movie {
        const data = this.getMoviesByStorage();
        return data[name];
    }

    deleteByName(name: string): boolean {
        const data = this.getMoviesByStorage();
        if (data[name] != null) {
            delete data[name];
            this.updateStorage(data);
            return true;
        }
        return false;
    }

    insert(movie: Movie): boolean {
        const data = this.getMoviesByStorage();
        if (data[movie.name] != null) {
            return false;
        }
        data[movie.name] = movie;
        this.updateStorage(data);
        return true;
    }

    update(movie: Movie): boolean {
        const data = this.getMoviesByStorage();
        if (data[movie.name] == null) {
            return false;
        }
        data[movie.name] = movie;
        this.updateStorage(data);
        return true;
    }

    private getMoviesByStorage(): { [key: string]: Movie } {
        return JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY));
    }

    private updateStorage(movies: { [key: string]: Movie }): void {
        localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(movies));
    }
}