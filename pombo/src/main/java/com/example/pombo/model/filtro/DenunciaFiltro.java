package com.example.pombo.model.filtro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.pombo.model.entity.Denuncia;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class DenunciaFiltro extends BaseFiltro implements Specification<Denuncia> {

    private String texto;
    private String idUsuario;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private String motivoDenuncia;
    private boolean analisado;

    public boolean temFiltro() {
        return  (filtroValido(this.texto))
                || (filtroValido(this.idUsuario))
                || (dataInicial != null)
                || (dataFinal != null)
                || (filtroValido(motivoDenuncia))
                || analisado;
    }

    @Override
    public Predicate toPredicate(Root<Denuncia> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if(this.getTexto() != null && this.getTexto().trim().length() > 0) {
            predicates.add(cb.like(root.get("mensagem").get("texto"), "%" + this.getTexto() + "%"));
        }

        if(this.getIdUsuario() != null && this.getIdUsuario().trim().length() > 0) {
            predicates.add(cb.like(root.get("publicador").get("id"), "%" + this.getIdUsuario() + "%"));
        }

        aplicarFiltroPeriodo(root, cb, predicates, this.getDataInicial(), this.getDataFinal(), "criadoEm");

        if(this.getMotivoDenuncia() != null && this.getMotivoDenuncia().trim().length() > 0) {
            predicates.add(cb.like(root.get("motivo"), "%" + this.getMotivoDenuncia() + "%"));
        }

        predicates.add(cb.equal(root.get("foiAnalisada"), this.isAnalisado()));

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}