import { Component, OnInit, NgZone, ViewChild, ElementRef } from '@angular/core';
import { MovieService } from '../../service/movie-service';
import { Movie } from '../../entity/movie';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies: Movie[];

  queryName: string;

  @ViewChild("modal")
  modal: ElementRef<HTMLDivElement>;

  modalMessage:string;

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    const span: HTMLSpanElement = this.modal.nativeElement.getElementsByClassName("close")[0] as HTMLSpanElement;
    span.onclick = () => {
      this.modal.nativeElement.style.display = "none";
    }
    this.loadItems();
  }

  add() {
    this.router.navigate(["movie"], { relativeTo: this.activatedRoute.parent });
  }

  edit(movie: Movie) {
    this.router.navigate(["movie/" + movie.name], { relativeTo: this.activatedRoute.parent });
  }

  remove(movie: Movie) {
    const res = this.movieService.deleteByName(movie.name);
    if (res) {
      this.modalMessage = "Deleted successfully.";
      this.search();
    } else {
      this.modalMessage = "failed to delete.";
    }
    this.modal.nativeElement.style.display = "block";
  }

  search() {
    this.loadItems(this.queryName);
  }

  showAbout() {
    this.router.navigate(["about"], { relativeTo: this.activatedRoute.root });
  }

  showHelp() {
    this.router.navigate(["help"], { relativeTo: this.activatedRoute.root });
  }

  private loadItems(queryName?: string) {
    if (queryName == null) {
      this.movies = this.movieService.getAll();
    } else {
      this.movies = this.movieService.queryByName(queryName);
    }
  }
}
