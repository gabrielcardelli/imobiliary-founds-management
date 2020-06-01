package project.mundofii.repository.predicates;

import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import project.mundofii.domain.FiiType;
import project.mundofii.domain.QFii;
import project.mundofii.repository.params.FiiSearchParams;

public class FiiPredicate {
	
	public static Predicate get(FiiSearchParams params) {
		
		QFii fii = new QFii("fii");
		
		BooleanBuilder where = new BooleanBuilder();
		
		if(!StringUtils.isEmpty(params.getType())) {
			where = where.and(fii.type.eq(FiiType.valueOf(params.getType())));
		}
		
		return where;
		
	}

}
