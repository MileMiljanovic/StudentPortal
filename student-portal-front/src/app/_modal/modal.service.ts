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
    paramFormular = this.placeholderFormular;
    paramDepartman = this.placeholderDepartman;
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

    close(id: string) {
        // close modal specified by id
        const modal = this.modals.find(x => x.id === id);
        modal.close();
    }
}
