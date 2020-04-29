import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { MovieService } from '../../service/movie-service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { switchMap } from "rxjs/operators";
import { Movie } from '../../entity/movie';
import { Location } from '@angular/common';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {

  movie: Movie;
  isEditing: boolean;

  @ViewChild("modal")
  modal: ElementRef<HTMLDivElement>;

  modalMessage: string;

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private location: Location,
  ) { }

  ngOnInit() {
    const span: HTMLSpanElement = this.modal.nativeElement.getElementsByClassName("close")[0] as HTMLSpanElement;
    span.onclick = () => {
      this.modal.nativeElement.style.display = "none";
    }

    this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        const name = params.get("name");
        if (name == null) {
          this.movie = new Movie();
          this.isEditing = false;
        } else {
          this.movie = this.movieService.getDetailByName(name);
          this.isEditing = true;
        }
      }
    );
  }

  save() {
    if (this.isEditing) {
      if (this.movieService.update(this.movie)) {
        this.modalMessage = "updated successfully.";
      } else {
        this.modalMessage = "failed to update.";
      }
    } else {
      if (this.movieService.insert(this.movie)) {
        this.modalMessage = "inserted successfully.";
        this.isEditing = true;
      } else {
        this.modalMessage = "failed to insert.";
      }
    }
    this.modal.nativeElement.style.display = "block";
  }

  setYear(str: string) {
    const temp = parseInt(str);
    this.movie.year = Number.isNaN(temp) ? (this.movie.year == null ? 0 : this.movie.year) : temp;
  }

  goBack(): void {
    // this.router.navigate(["movies"], { relativeTo: this.activatedRoute.parent })
    this.location.back();
  }

  showAbout() {
    this.router.navigate(["about"], { relativeTo: this.activatedRoute.root });
  }

  showHelp() {
    this.router.navigate(["help"], { relativeTo: this.activatedRoute.root });
  }
}
