import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ModalService {
    private modals: any[] = [];
    placeholderFormular = {idformular: 'placeholder',
            student: {
                brindeksa: 'placeholder',
                studije: {
                    naziv: 'placeholder'
                }
            },
            programStrani: {
                naziv: 'placeholder'
            },
            departmanId: 'placeholder',
            koordinator: {
                username: 'placeholder'
            }
    };
    placeholderDepartman = {
        departmanId: 'placeholder',
            koordinator: {
                username: 'placeholder'
        }
    };
    placeholderKorisnik = {
        username: 'placeholder',
        password: 'placeholder',
        ime: 'placeholder',
        prezime: 'placeholder',
        jmbg: 'placeholder',
        datumrodjenja: 'placeholder',
        uloga: 'placeholder'
    };
    placeholderNastavnik = {
        nastavnikid: 'placeholder',
        ime: 'placeholder',
        prezime: 'placeholder',
        jmbg: 'placeholder',
        datumrodjenja: 'placeholder',
        email: 'placeholder'
    };
    placeholderStudent = {
        brindeksa: 'placeholder',
        ime: 'placeholder',
        prezime: 'placeholder',
        jmbg: 'placeholder',
        datumrodjenja: 'placeholder',
        email: 'placeholder',
        studije: {
            naziv: 'placeholder'
        }
    };
    placeholderPredmetDom = {
        predmetId: 'placeholder',
        naziv: 'placeholder',
        program: {
            naziv: 'placeholder',
            departman: {
                departmanId: 'placeholder'
            },
            sef: {
                username: 'placeholder'
            }
        },
        espb: 'placeholder',
        nastavnik: {
            nastavnikid: 'placeholder'
        }
    };
    placeholderPredmetStr = {
        predmetId: 'placeholder',
        naziv: 'placeholder',
        program: {
            naziv: 'placeholder'
        },
        espb: 'placeholder'
    };
    placeholderProgramDom = {
        naziv: 'placeholder',
        departman: {
            departmanId: 'placeholder'
        },
        sef: {
            username: 'placeholder'
        }
    };
    placeholderZamena = {
        idzamena: 'placeholder',
        formular: 'placeholder',
        predmetDomaci: {
            predmetId: 'placeholder',
            naziv: 'placeholder',
            program: {
                naziv: 'placeholder',
                departman: {
                    departmanId: 'placeholder'
                },
                sef: {
                    username: 'placeholder'
                }
            },
            espb: 'placeholder',
            nastavnik: {
                nastavnikid: 'placeholder'
            }
        },
        predmetStrani: {
            predmetId: 'placeholder',
            naziv: 'placeholder',
            program: {
                naziv: 'placeholder'
            },
            espb: 'placeholder'
        },
        odobreno: 'placeholder',
        token: 'placeholder'
    };
    paramFormular = this.placeholderFormular;
    paramDepartman = this.placeholderDepartman;
    paramKorisnik = this.placeholderKorisnik;
    paramNastavnik = this.placeholderNastavnik;
    paramStudent = this.placeholderStudent;
    paramPredmetDom = this.placeholderPredmetDom;
    paramPredmetStr = this.placeholderPredmetStr;
    paramProgramDom = this.placeholderProgramDom;
    paramZamena = this.placeholderZamena;
    add(modal: any) {
        // add modal to array of active modals
        this.modals.push(modal);
    }

    remove(id: string) {
        // remove modal from array of active modals
        this.modals = this.modals.filter(x => x.id !== id);
    }

    open(id: string) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        modal.open();
    }

    openWithParamFormular(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramFormular = param;
        modal.open();
    }

    openWithParamDepartman(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramDepartman = param;
        modal.open();
    }

    openWithParamKorisnik(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramKorisnik = param;
        modal.open();
    }

    openWithParamNastavnik(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramNastavnik = param;
        modal.open();
    }

    openWithParamStudent(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramStudent = param;
        modal.open();
    }

    openWithParamPredmetDom(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramPredmetDom = param;
        modal.open();
    }

    openWithParamPredmetStr(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramPredmetStr = param;
        modal.open();
    }

    openWithParamProgramDom(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramProgramDom = param;
        modal.open();
    }

    openWithParamZamena(id: string, param) {
        // open modal specified by id
        const modal = this.modals.find(x => x.id === id);
        this.paramZamena = param;
        modal.open();
    }

    close(id: string) {
        // close modal specified by id
        const modal = this.modals.find(x => x.id === id);
        modal.close();
    }
}
