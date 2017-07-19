package com.gmail.acharne1985.service;

import com.gmail.acharne1985.models.Section;

public interface SectionService extends ModelService<Section>  {

    Section sectionJoinStudent(Integer id);

}
